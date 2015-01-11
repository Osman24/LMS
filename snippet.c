
      #include <fcntl.h>
      #include <errno.h>
      #if !defined(__MINGW32__)
        #include <err.h>
      #else

      #endif
      #include "mman.c"
      #include <sys/stat.h>
      #include <stdio.h>
      #include <stdint.h>
      #ifndef MAP_FILE
      #define MAP_FILE MAP_SHARED
      #endif
      int fsize(int fd) {
        struct stat stat;
        int res = fstat(fd,&stat);
        return stat.st_size;
      }
      int printll(char* s) {
        while (*s != '\n' && *s != ',' && *s != '\t') {
          putchar(*s++);
        }
        return 0;
      }
      long hash(char *str0, int len)
      {
        unsigned char* str = (unsigned char*)str0;
        unsigned long hash = 5381;
        int c;

        while ((c = *str++) && len--)
          hash = ((hash << 5) + hash) + c; /* hash * 33 + c */

        return hash;
      }
      char* copyBuffer(char* buffer, int len)
      {
        char* newBuffer;
        newBuffer = (char*) malloc (len+1);
        memcpy(newBuffer, buffer, len * sizeof(char));
        newBuffer[len] = '\0';
        return newBuffer;
      }
      char** splitLine(char* buffer, char delimeter, int len)
      {
        char** lineArray;
        int i =0;
        int length = 0;
        int currentColumn = 0;
        lineArray = (char**)malloc( len * sizeof(char*));
        while(buffer[i] != '\0'){

          i++;
        }
        return lineArray;
      }
      //void Snippet(char*);
      int main(int argc, char *argv[])
      {
        if (argc != 2) {
          printf("usage: query <filename>\n");
          return 0;
        }
        Snippet(argv[1]);
        return 0;
      }

                     
/*****************************************
  Emitting C Generated Code                  
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void Snippet(char*  x0) {
int32_t x1 = 0;
int32_t x5 = 0;
int32_t x6 = 0;
bool x7 = false;
bool x8 = false;
int32_t x2 = open(x0,0);;
int32_t x3 = fsize(x2);
char* x4 = mmap(0, x3, PROT_READ, MAP_FILE | MAP_SHARED, x2, 0);
for (;;) {
int32_t x9 = x5;
bool x10 = x9 < x3;
if (!x10) break;
int32_t x12 = x5;
for (;;) {
int32_t x13 = x5;
char x14 = x4[x13];
bool x16 = x14 == ',';
bool x18;
if (x16) {
x18 = false;
} else {
bool x17 = x14 != '\n';
x18 = x17;
}
bool x20;
if (x18) {
bool x19 = x13 < x3;
x20 = x19;
} else {
x20 = false;
}
if (!x20) break;
x5 += 1;
}
int32_t x25 = x5;
int32_t x26 = x25 - 1;
x6 = x26;
char x28 = x4[x25];
bool x29 = x28 == '\n';
if (x29) {
x6 = true;
} else {
}
for (;;) {
int32_t x33 = x5;
char x34 = x4[x33];
bool x36 = x34 == ',';
bool x38;
if (x36) {
x38 = false;
} else {
bool x37 = x34 != '\n';
x38 = x37;
}
bool x40;
if (x38) {
bool x39 = x33 < x3;
x40 = x39;
} else {
x40 = false;
}
if (!x40) break;
x5 += 1;
}
int32_t x45 = x5;
int32_t x46 = x45 - 1;
x6 = x46;
char x48 = x4[x45];
bool x49 = x48 == '\n';
if (x49) {
x6 = true;
} else {
}
int32_t x53 = x45 - x45;
int32_t x54 = printf("%.*s",x53,x45+x4);
}
}
/*****************************************
  End of C Generated Code                  
*******************************************/

