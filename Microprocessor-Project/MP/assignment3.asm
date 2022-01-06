
include 'emu8086.inc'
.model small
.code

start:
print 'length'
printn

mov ah,01h
int 21h  ; stored length number in al register


mov ah,0
sub al,'0'
mov cx,ax ; copy ax value in cx for controlling loop      
mov dx,ax ; keep loop number in dx as ax and cx will change
printn

mov bx,0   
input:
    mov ah,01h
    int 21h  ; stored in al 
    cmp al,97 ; comparing input with 'a'
    jb skp
    cmp al,122 ; comparing input with 'z'
    ja skp
        
    sub al,32  ; as the difference of lower and upper case is 32
    
    skp:     
    mov [2000+bx],al ; starting memory location is 2000
    inc bx ; changing memory location by increasing bx      
loop input

printn 
 
mov cx,dx ; copy loop number from dx to cx
mov bx,0
output:
    mov dl,[2000+bx] ; collecting numbers from memory location
    mov ah,02h ; stored in dl
    int 21h  ; output number from dl
    inc bx
loop output   

end start