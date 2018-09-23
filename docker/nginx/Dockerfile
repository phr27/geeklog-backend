FROM nginx
RUN mkdir -p /usr/local/nginx/logs/ && \
    touch /usr/local/nginx/logs/access.log && \
    rm -rf /etc/nginx/conf.d/default.conf && \
    mkdir -p /home/ftpfile && \
    chmod 777 /home/ftpfile
COPY ./docker/nginx/*.conf /etc/nginx/conf.d/
CMD ["nginx", "-g", "daemon off;"]