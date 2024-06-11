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

            produs.denumire=(char*)malloc(sizeof(char)*(strlen(denumire)+1));
            strcpy(produs.denumire,denumire);

        }

        return produs;
}

void newProducVec(FILE* stream,unsigned int *size,Produs **vector)
{
    if(stream)
    {
        printf("%d",*size);
        while(!feof(stream))
        {
            printf("%d",*size);
            *vector = (Produs*)realloc((*vector),(((*size)+1))*sizeof(Produs));
            (*vector)[*size]=newProduct(stream);
            (*size)++;
            
        }
    }

};


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

void citireProdus(Produs *produs)
{
    char buffer[100];
    printf("Pret: ");
    scanf("%f",&((*produs).pret));
    printf("Id: ");
    scanf("%d",&((*produs).id));
    printf("Denumire: ");
    scanf("%s",buffer);

    (*produs).denumire=(char*)malloc(strlen(buffer)+1);
    strcpy((*produs).denumire,buffer);
}


void citireElemente(Produs ***mat,int n,int m)
{
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
    {
        printf("Produs %d %d:\n",i,j);
        citireProdus(&((*mat)[i][j]));
    }
}

void citireMat(Produs ***mat,int *n,int*m)
{
    printf("Nr Linii: ");
    scanf("%d",n);
    printf("Nr Coloane: ");
    scanf("%d",m);
    *mat=(Produs**)malloc((*n)*sizeof(Produs));
    for(int i=0;i<*n;i++)
    {
        (*mat)[i]=(Produs*)malloc((*m)*sizeof(Produs));
    }
    citireElemente(mat,*n,*m);
}

void  afisareMat(Produs **mat,int n,int m)
{
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            printf("Produs %d %d :\n",i,j);
            afisareProdus(mat[i][j]);
            
        }
        
    }
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

/*
fscanf(input,"%d",&val1);
fscanf(input,"%d",&val2);
fscanVec(inputVec,&vector,&n);
*/

/*
printf("Val1=%d Val2=%d \n",val1,val2);
afisareVector(vector,n);
*/

//afisareProdus(produs);

/*
if(vector)
    free(vector);
*/

fclose(inputProd);
fclose(inputVec);
fclose(input);


//3

FILE *vecInput=fopen("Input/produse.txt","rt");
Produs* vectorProds=NULL;
unsigned int size=0;


newProducVec(vecInput,&size,&vectorProds);


for(int i=0;i<size;i++)
{
    afisareProdus(vectorProds[i]);
}

 Produs **p;
 int nrLinii=0,nrCol=0;
 citireMat(&p,&nrLinii,&nrCol);
 afisareMat(p,nrLinii,nrCol);

fclose(vecInput);

    return 0;
}