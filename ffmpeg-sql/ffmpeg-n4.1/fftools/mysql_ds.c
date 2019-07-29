
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>
#include "mysql_ds.h"

//MYSQL *connection = NULL;
//MYSQL mysql;

const char *ip_default = "192.168.152.38";
const char *uid_default = "root";
const char *pwd_default = "spirit";
const char *db_default = "translate";


MYSQL *init_connector(char *ip=NULL, char *uid=NULL, char *pwd=NULL, char *db=NULL)
{
	MYSQL mysql;
	MYSQL *fd = NULL;
    mysql_init(&mysql);
	if (ip == NULL) {
		fd = get_fd(ip, uid, pwd, db);
	}
	else {
		fd = get_fd(ip_default, uid_default, pwd_default, db_default);
	}
	return fd;
}

static MYSQL *get_fd(const char *hostname, const char *username, const char *password,
        const char *dbname) {
    MYSQL *fd = mysql_real_connect(&mysql, hostname, username, password, dbname, 0, 0, 0);

    if (fd == NULL)
    {
        printf("%s\n", mysql_error(&mysql));
        return -1;
    }

    printf("success connect to mysql\n");
    
    return fd;
}

void disconn_db(MYSQL *fd)
{
    if (fd)
    {
        mysql_close(fd);
        fd = NULL;
    }
}

int query_sql(MYSQL *fd, const char *SQL)
{
    int state = mysql_query(fd, SQL);
    if (state != 0)
    {
        printf("%s\n", mysql_error(fd));
        return -1;
    }

    MYSQL_RES *result = mysql_store_result(fd);
    if (result == (MYSQL_RES *) NULL)
    {
        printf("%s\n", mysql_error(fd));
        return -1;
    } else
    {
        MYSQL_FIELD *sqlField;
        int iFieldCount = 0;
        while (1)
	{
            sqlField = mysql_fetch_field(result);
            if (sqlField == NULL)
                break;
            printf("%s\t", sqlField->name);
		iFieldCount++;
        }
        printf("\n");
	MYSQL_ROW sqlRow;
        while (1)
        {
            sqlRow = mysql_fetch_row(result);
            if (sqlRow == NULL)
                break;
            int i;
            for (i = 0; i < iFieldCount; i++) {
                if (sqlRow[i] == NULL)
                    printf("NULL\t");
                else
                    printf("%s\t", (const char *)sqlRow[i]);
	}
            printf("\n");
}
        printf("query is ok, %u rows affected\n", (unsigned int)mysql_affected_rows(fd));
        mysql_free_result(result);
    }
    return 0;
}

int exec_sql(MYSQL *fd, const char *SQL)
{
    int state = mysql_query(fd, SQL);
    if (state != 0)
    {
        printf("%s\n", mysql_error(fd));
        return -1;
    }
    printf("query is ok, %u rows affected\n", (unsigned int)mysql_affected_rows(fd));
    return 0;
}
