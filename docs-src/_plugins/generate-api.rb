# encoding: utf-8
#
# Title:
# ======
# Events JSON Generator
#
# Description:
# ============
# Generates events json
#
# Author:
# ======
# Rbin Hos

module Jekyll
  require 'json'

  class DataPage < PageWithoutAFile

  end

  class JSONGenerator < Generator
    safe true
    priority :low

    def generate(site)
      baseDir = "api/v1"

      @site = site

      file = File.read(File.join(site.source, "_data", "entities.json"))

      @entities = JSON.parse(file)

      @schemas = {
        "events" => {
          "participants" => {
            "mapper" => lambda { | participant | 
              id = participant["id"]
              type = participant["type"]
              number = participant["number"]
              result = participant["result"]
              formattedResult = participant["formattedResult"]

              item = {
                "competitor" => {
                  "id" => id,
                  "type" => type,
                  "name" => @entities["#{type}s"][id]["name"]
                },
                "result" => result,
                "formattedResult" => formattedResult
              }

              item["number"] = number unless number.nil?

              item
            }
          }
        },
        "teams" => {
          "members" => {
            "mapper" => lambda { | member | { "name" => @entities["persons"][member["id"]]["name"] }.merge(member) }
          }
       }
      }

      ["events", "teams", "persons"].each { | name |
        generateEntities baseDir, name, @entities[name]
      }

    end

    def generateEntities(dir, name, data, meta = nil)
      if (data.nil?)
         return nil
      end

      items = nil.to_a

      entityDir = File.join(dir, name)

      data.entries.each { | id, attributes | 
        item = generateEntity entityDir, name, id, attributes, meta
        items.push(item)
      }

      endpoint dir, name, items
    end

    def generateEntity(dir, name, id, attributes, meta = nil)
      schema = @schemas[name]

      item = nil

      if schema.nil?
        item = { "id" => id }.merge(attributes)
      else
        item = { "id" => id }.merge(attributes).select { | key, value | !schema.keys.include? key }

        relationDir = File.join(dir, id)

        schema.entries.each { | relationName, relationMeta |
          generateEntities relationDir, relationName, attributes[relationName], relationMeta
        }
      end

      if !meta.nil?
        item = meta["mapper"].(item)
      end
      
      endpoint(dir, id, item) unless schema.nil?

      item
    end

    def endpoint(dir, name, data)
      page = DataPage.new(@site, @site.source, dir, "#{name}.json")
      page.data["layout"] = nil
      page.content = JSON.pretty_generate(data)

      @site.pages << page
    end

  end


end