#ifndef __MYSQL_DS_H__
#define __MYSQL_DS_H__
#include "mysql/mysql.h"

MYSQL *init_connector(char *ip=NULL, char *uid=NULL, char *pwd=NULL, char *db=NULL)
void disconn_db(MYSQL *fd);
int query_sql(MYSQL *fd, const char *SQL);
int exec_sql(MYSQL *fd, const char *SQL);

#endif
