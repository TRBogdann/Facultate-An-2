#include <stdio.h>
#include <malloc.h>

unsigned long strlen(const char* str)
{
    unsigned long i = 0;
    while(str[i]!=0)
    {
        i++;
    }
    return i;
}
char* strchr(const char* str,int ch)
{
    int i = 0;
    while(str[i]!=0)
    {
        if(str[i]==ch)
            return (char*)str+i;
        i++;
    }
    return NULL;
}

char* strtok(char *str,const char *separators)
{
    static char* copy = NULL;
   
    int i = 0;
    if(str == NULL)
    {
        str = copy;
    }
    
    while(str!=NULL && !strchr(separators,str[i]) && str[i]!=0)
    {
        i++;
    }
    
    if(str!=NULL && str[i]!=0)
    {
        copy = str+i+1;
        str[i] = 0;
    }
    else 
        copy = NULL;

    
    return str;
}

char* strcpy(char* target,const char* source)
{
    int i = 0;
    while(source[i]!=0)
    {
        target[i] = source[i];
        i++;
    }
    target[i] = 0;

    return target;

}


int main()
{
    char *string = (char*)malloc(strlen("Alb,Negru,Rosu")+1);
    strcpy(string,"Alb,Negru,Rosu");
   
    char *token = strtok(string,",");
   
     while(token!=NULL)
    {
        printf("%s\n",token);
        token = strtok(NULL,",");
    }  


    return 0;
}