include 'emu8086.inc'
.model small
.code

start:

    print 'Numbers'
    printn
    mov ah,01h   ; taking input in al
    int 21h  
    
    mov cl,al ; stored in cx for controlling loop
    sub cl,'0'
    mov ch,0  
    
    printn
    print 'Enter single digit number'
    printn
    mov bl,0  ;max number in bx is initialized to zero
input:
    printn
    mov ah,01h   ; taking input in al
    int 21h 
    cmp al,bl  
    jb skp ;al<lb
    mov bl,al   ; keeps max number in bl
    skp:
    
loop input

    printn
    print 'largest digit '
    mov dl,bl
    mov ah,02h  ; output answer from dx
    int 21h
    printn

end start