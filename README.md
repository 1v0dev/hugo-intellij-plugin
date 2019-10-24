# hugo-intellij-plugin

Hugo static site generator plugin for Intellij

**Features**:
* Hugo Run Configuration to start Hugo Server or execute any other Hugo command with optional arguments
* Added action to New group of Project View context menu to execute `hugo new`
* Allow to manually set the path to Hugo executable in `Settings - Tools - Hugo Plugin`

*Note: If the Hugo executable is not in your* `PATH` *variable, you have to set the path in the settings*

**Planned features**
* Hugo Facet to automatically add run configuration when starting a new project
* Toggles for the most used arguments so they don't have to be manually typed in the arguments field
* Option to set default arguments to be passed to `hugo new`