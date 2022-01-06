include 'emu8086.inc'
.model small
.code

start:

    print 'input how many numbers'
    printn
    call getc
    mov cl,al ;the  low 8 bit has the  input
    sub cl,'0';made it integer
    mov ch,0  ;high 8 bits are set to zero
    
    printn
    print 'Enter numbers [single digit]'
    printn
    

mov bl,0  ;bx initialized to zero
input:
    printn
    call getc
    cmp al,bl  
    jb donothing ;al<lb
    
    assign:
        mov bl,al
    
    donothing:
    
loop input
 
    
output:
    printn
    print 'largest digit '
    mov dl,bl
    call putc
    printn


mov ax,4c00h
int 21h



getc:
mov ah,01h
int 21h
ret

putc:
mov ah,02h
int 21h

end start