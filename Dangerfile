####
#
# Danger
#
####
# reference: https://danger.systems/reference.html

# Use to ignore inline messages which lay outside a diff's range, thereby not posting them in the main comment.
github.dismiss_out_of_range_messages

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 300

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

####
#
# danger-checkstyle_format
#
####
checkstyle_format.base_path = Dir.pwd

# detekt
checkstyle_format.report 'build/reports/detekt/detekt.xml'
