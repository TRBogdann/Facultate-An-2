#include <stdio.h>
#include <malloc.h>
#include <string.h>

int main()
{
    char* str = (char*)malloc(10);
    int* adr1 = (int*)str;
    strcpy(str,"MyString");
    char *str2 = (char*)malloc(10);
    unsigned long aloc = str2-str;

    str = (char*)realloc(str,strlen(str)+2);
    int* adr2 = (int*)str;

    str = (char*)realloc(str,strlen(str)+5+aloc);
    int* adr3 = (int*)str;
    printf("%s\n",str);
    printf("%p\n",adr1);
    printf("%p\n",adr2);
    printf("%p\n",adr3);
    printf("%s\n",str);
    printf("%ld",aloc);
    return 0;
}