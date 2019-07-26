
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>
#include "mysql_ds.h"

MYSQL *connection = NULL;
MYSQL mysql;

void init_db()
{
    mysql_init(&mysql);
}

MYSQL *get_fd(const char *hostname, const char *username, const char *password,
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
