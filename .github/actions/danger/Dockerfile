FROM ruby:2.6.3-stretch

RUN gem install danger -v '>= 5.10.3'
RUN gem install danger-checkstyle_format

ENTRYPOINT "danger"
CMD "--verbose"
