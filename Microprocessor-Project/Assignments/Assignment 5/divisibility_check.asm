include 'emu8086.inc'
.model small
.code

start:

call inputnumber
call divisibility_check

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
;push cx ; push the length in stack for later use
;mov [2000],cx

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




;input has to be in ax
divisibility_check:
print 'enter the number for divisibility check'
printn

push ax ;input is stored in stack

call getc
mov ah,0
sub al,'0'
mov bx,ax ; divider is stored in bx
printn

pop ax
div bx ; ax=ax/bx;dx=ax%bx
cmp dx,0
je match
jmp nomatch
 
match:
    printn 'the number is divisible'
    jmp skip
    
nomatch:
    printn 'the number is not divisible'
skip:

ret





getc:
mov ah,01h
int 21h  ;input is stored in al
ret

putc:
mov ah,02h ; output must be stored in dl
int 21h
ret

end start