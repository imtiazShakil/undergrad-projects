include 'emu8086.inc'
.model small
.code

start:
print 'length'
printn

mov ah,01h
int 21h  ; stored length in al register


mov ah,0
sub al,'0'
mov cx,ax  ; keep ax in cx for controlling loop     
mov dx,ax  ; keep ax in dx as the value of ax will change
printn

mov bx,0
input:
   mov ah,01h   ; collecting input
   int 21h      ; stored in al 
   mov [2000+bx],al ; here starting memory location  is 2000
   inc bx  ; changing the memory location by increasing bx
loop input

printn 
 
mov cx,dx  ; copy loop number in cx from dx
mov bx,0
output:
    mov dl,[2000+bx] ; collect input from memory location
    mov ah,02h ; stored in dl
    int 21h
    inc bx
loop output   

end start