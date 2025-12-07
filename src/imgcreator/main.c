#include <stdio.h>
#include <stdlib.h>

FILE *vgaFile;
int returnCode=0;

char ff = 0xFF;
char header[] = {0x56,0x47,0x41,       0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF}; //13bytes padding

int maxWidth = 640;
int maxHeight = 480;
int width=0;
int height=0;


void writeHeader(){
    fwrite(header,sizeof(unsigned char),sizeof(header), vgaFile);
}

void writeData(){
    for(width=0;width<maxWidth;width++){
        fputc(0x00, vgaFile);
    }
    if(width>=maxWidth){
        width=0;
        fputc(0x0A,vgaFile);
        height++;
    }
    if(height==maxHeight){
        printf("Done.");
        exit(0);
    }
    writeData();
}

int main(){
    vgaFile = fopen("new.vga","w");
    writeHeader();
    writeData();
    return 0;
}
