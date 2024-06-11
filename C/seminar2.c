#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
float pret;
char* denumire;
int id;
}Produs;

char fscanVec(FILE* stream,int** vector,int* dimensiune)
{
    if(stream==NULL)
        return 1;

    fscanf(stream,"%d",dimensiune);
    *vector=(int*)malloc(sizeof(int)*(*dimensiune));

    for(int i=0;i<*dimensiune;i++)
    {
        fscanf(stream,"%d",&(*vector)[i]);
    }

    return 0;
}

Produs newProduct(FILE* stream)
{
    Produs produs;
    if(stream!=NULL)
        {
                
            char buffer[30];
            unsigned int len;
            /*
            fscanf(stream,"%f",&produs.pret);
            fscanf(stream,"%d",&produs.id);
            */
            fgets(buffer,30,stream);
            produs.pret=atof(buffer);

            fgets(buffer,30,stream);
            produs.id=atoi(buffer);

            fgets(buffer,30,stream);
            
            char* denumire=strtok(buffer,"\n");

            produs.denumire=malloc(sizeof(char)*(strlen(denumire)+1));
            strcpy(produs.denumire,denumire);

        }

        return produs;
}

void afisareVector(int* vector,int dimensiune)
{
    for(int i=0;i<dimensiune;i++)
    {
        printf("V[%d]=%d\n",i,vector[i]);
    }

}

void afisareProdus(Produs produs)
{
    printf("Id=%d \nPret=%.2f \nDenumire=%s\n",produs.id,produs.pret,produs.denumire);
}

int main()
{

int val1=0, val2=0;
int *vector;
int n;

FILE *input = fopen("Input/in.txt","rt");
FILE *inputVec = fopen("Input/vector.txt","rt");
FILE *inputProd = fopen("Input/produs.txt","rf");

Produs produs=newProduct(inputProd);

if(input==NULL || inputVec==NULL || inputProd==NULL)
    return 1;

fscanf(input,"%d",&val1);
fscanf(input,"%d",&val2);
fscanVec(inputVec,&vector,&n);


printf("Val1=%d Val2=%d \n",val1,val2);
afisareVector(vector,n);
afisareProdus(produs);

if(vector)
    free(vector);

fclose(inputProd);
fclose(inputVec);
fclose(input);

    return 0;
}