include 'emu8086.inc'
.model small
.code

start:

call inputnumber

mov ax,4c00h
int 21h

inputnumber:
print 'How many digits ?'
printn
call getc
printn
mov cx,0
mov cl,al
sub cl,'0'

mov ax,0
number:

push ax  ;push now value to stack

call getc

sub al,'0'
mov bx,0
mov bl,al    ; input is in bx

pop ax      ;ax=previous number
mov dx,10

mul dx ;dx=ax=ax*10

add ax,bx  ;ax =ax + bx

loop number
printn
;output is stored in ax
ret

getc:
mov ah,01h
int 21h   ; output is stored in al
ret

putc:
mov ah,02h ; target value must be in dx/dl
int 21h
ret

end start