
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
int32_t x7 = 0;
bool x8 = false;
bool x9 = false;
int32_t x2 = open(x0,0);;
int32_t x3 = fsize(x2);
char* x4 = mmap(0, x3, PROT_READ, MAP_FILE | MAP_SHARED, x2, 0);
int* *x16 = (int32_t[]){0,1};
for (;;) {
int32_t x10 = x5;
bool x11 = x10 < x3;
if (!x11) break;
int32_t x13 = x5;
x6 = x13;
int32_t x15 = 0;
bool x17 = true;
for (;;) {
int32_t x18 = x6;
char x19 = x4[x18];
bool x21 = x19 == '\n';
bool x23;
if (x21) {
x23 = false;
} else {
bool x22 = x19 != 13;
x23 = x22;
}
bool x27;
if (x23) {
int32_t x24 = x5;
bool x25 = x24 < x3;
x27 = x25;
} else {
x27 = false;
}
if (!x27) break;
int32_t x29 = x6;
char x30 = x4[x29];
bool x31 = x30 == ',';
if (x31) {
x15 += 1;
} else {
int32_t x34 = x15;
bool x35 = x34 == 2;
if (x35) {
x17 = false;
} else {
int32_t x39 = x16[x34];
bool x40 = x39 == 1;
bool x44;
if (x40) {
bool x41 = x30 < '0';
bool x42 = x30 > '9';
bool x43 = x41 || x42;
x44 = x43;
} else {
x44 = false;
}
if (x44) {
x17 = false;
} else {
}
}
}
x6 += 1;
}
bool x56 = x17;
if (x56) {
for (;;) {
int32_t x57 = x5;
char x58 = x4[x57];
bool x60 = x58 == '\n';
bool x62;
if (x60) {
x62 = false;
} else {
bool x61 = x57 < x3;
x62 = x61;
}
if (!x62) break;
x5 += 1;
}
int32_t x67 = x5;
x5 += 1;
int32_t x68 = x67 - x13;
char* x70 = x4+x13;
int32_t x71 = printf("%.*s",x68,x70);
} else {
for (;;) {
int32_t x73 = x5;
char x74 = x4[x73];
bool x76 = x74 == '\n';
bool x78;
if (x76) {
x78 = false;
} else {
bool x77 = x73 < x3;
x78 = x77;
}
if (!x78) break;
x5 += 1;
}
int32_t x83 = x5;
x5 += 1;
}
}
}
/*****************************************
  End of C Generated Code                  
*******************************************/

