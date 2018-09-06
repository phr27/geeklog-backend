FROM registry.saas.hand-china.com/tools/mysql:5.6
COPY ./docker/mysql/geeklog* /
RUN cat /geeklog.sql > /docker-entrypoint-initdb.d/dump.sql