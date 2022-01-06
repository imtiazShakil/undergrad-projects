include 'emu8086.inc'
.model small
.code

start:
print 'convert uppercase to lowercase'
printn
printn 'enter length'
printn

call getc
mov cl,al
sub cl,'0'
mov ch,0
printn

mov bx,0
input:
    
    call getc
    cmp al,061h
    jb donothing
    cmp al,07ah
    ja donothing
    
    substract :
        sub al,32
    
    donothing:
        
    mov [2000+bx],al
    inc bx       
loop input

mov cx,bx
mov bx,0
printn
output:
    mov dl,[2000+bx]
    inc bx
    call putc    
loop output


mov ax,4c00h
int 21h

getc:
mov ah,01h
int 21h
ret

putc:

push ax
mov ah,02h
int 21h
pop ax
ret
 
end start
