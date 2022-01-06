include 'emu8086.inc'
.model small
.code

start:
print 'Enter length'
printn

call getc
mov ah,0
sub al,'0'
mov cx,ax
printn

mov bx,0
input:
   call getc
   mov [2000+bx],al
   inc bx
loop input

;make_new_line :


mov dl,13d ;13d moves  the cursor toimmediate bottom
call putc
       
mov dl,10d ;10d moves the cursor to the start
call putc

mov cx,6
mov bx,0
output:
    mov dl,[2000+bx]
    call putc
    inc bx
loop output   


;return to OS
mov ax,4c00h
int 21h


getc: ; input  character
mov ah,01h
int 21h  ; input is stored in al register
ret

putc: ; print character
mov ah,02h ; output must be stored in dl
int 21h
ret

end start