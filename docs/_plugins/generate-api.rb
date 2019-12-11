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

      endpoint site, "api/v1", "events"

      endpoint site, "api/v1", "teams"

      endpoint site, "api/v1", "persons", []

    end

    def endpoint(site, dir, name, data = nil)
      data = site.data[name] unless data
      page = DataPage.new(site, site.source, dir, "#{name}.json")
      page.data["layout"] = nil
      page.content = JSON.generate(data)

      site.pages << page
    end

  end


end