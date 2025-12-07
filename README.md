# VGA-img

# FAQ
**What is this?**
Its a custom uncompressed image format that includes VGA colors(16 colors).
**Is there real use for this**
no.
**Why are you using java? why not something faster like C/C++?**
I already do use c for creating the file,but i use java for displaying because theres no good UI API made for C or C++. i also hate C++. And GTK is way too hard for something like this.I couldve used OpenGL immeadiete since its easier but i dont want to deal with images and exporting those images to OpenGL. TL;DR no good UI that is easy.


# Technical Details

Resolution: 640x480
Colors:16
Magic Number: VGA( 56 47 41 in hex)
13 filler bytes in the header just for padding.
and after that comes the picture data. for the 16 colors i use the following hex numbers:
00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15
And yes i didnt use 0A,0B,0C,0D,0F at the end its because of simplicity and human readability since right now the only way to edit pictures in this is to use a hex editor.
I also use 0A as a way of saying for image viewers to go to the next line and start rendering pixels from there on.
The format itself is extremely simple, so simple that my c file for creating an image in it that is fully black is only 41 lines of code.

# For Image Viewers

VGAimg is supposed to be rendered horizontally,like my example in java. Everytime theres a 0x0A that means newline,go to the next line and start rendering the pixels horizonatally again.Please dont learn from my example as im no optimization king and my image viewer is extremely slow, i have an ryzen 9 9950x and even then i can literally see the pixels being generated,cant imagine how slow this would be on a 2008 machine.And yes of course its CPU rendered so even slower.Please use something like OpenGL or Vulkan to optimize rendering or just use a bufferedimage in java since i didnt want to. If you are willing to optimize the mess of a codebase this is go for it,just know i dont recommend it

# How to create images in the VGAimg format?

Well right now there is no picture editor that saves to this format so you have to use a hex editor,but first you need to compile and run the c code so that everything will work. Im gonna make a mspaint-like program in java that exports to the format.

# Installation/Compilation

Here are the commands to compile both the image viewer and the image creator.
**Image Viewer**

```javac viewer.java``` or ```java viewer.java```(compiles it JIT)

**Image Creator**

```gcc -O3 main.c -o imgcreator```

These were mainly for Linux since i dont know what compiler Windows or macOS uses so just use WSL or cygwin GCC on Windows or idk clang or LLVM on macOS if it even allows you to do so.
