#ifndef __MYSQL_DS_H__
#define __MYSQL_DS_H__
#include <mysql/mysql.h>

MYSQL *init_connector(MYSQL *mysql, const char *ip, const char *uid, const char *pwd, const char *db);
void disconn_db(MYSQL *fd);
int query_sql(MYSQL *fd, const char *SQL);
int exec_sql(MYSQL *fd, const char *SQL);

#endif
