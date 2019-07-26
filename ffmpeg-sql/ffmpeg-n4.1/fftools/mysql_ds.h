#ifndef __MYSQL_DS_H__
#define __MYSQL_DS_H__
#include "mysql/mysql.h"

void init_db();
MYSQL *get_fd(const char *hostname, const char *username, const char *password,
        const char *dbname);
void disconn_db(MYSQL *fd);
int query_sql(MYSQL *fd, const char *SQL);
int exec_sql(MYSQL *fd, const char *SQL);

#endif
