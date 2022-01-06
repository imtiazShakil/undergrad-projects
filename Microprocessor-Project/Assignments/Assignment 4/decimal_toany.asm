include 'emu8086.inc'
.model small
.code


start:


;----------------------------------------------------------
call inputnumber ; take input which has to be stored in ax
push ax  ; the number is stored in stack

print 'Enter base ?'
printn
call inputnumber ; base has been taken as input in ax

mov bx,ax ; base is stored in bx
pop ax ; actual number is restored in ax

call decimal_other


mov ax,4c00h
int 21h

;-----------------------------------------------------------

; input must be stored in ax
; base must be stored in bx
decimal_other:
mov cx,10

base:
    div bx ; ax=ax/bx ;dx=ax%bx
    push dx
    mov dx,0
loop base

mov cx,10
prnt:
    pop dx
    cmp dx,9
    ja add_1
    jb add_0
    
    add_1:
        add dx,55
        jmp skip
    add_0:
        add dx,'0'
    

skip:
    call putc
    
loop prnt    
ret

;output will be stored in ax
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



putc:
 mov ah,02h
 int 21h
 ret
 
getc:
mov ah,01h
int 21h
ret
end start