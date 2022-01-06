include 'emu8086.inc'
.model small
.code

start:

;---------------------------------------------------------

call inputnumber ;enter the number that is to be converted in decimal
push ax ; number stored in stack


print 'Enter The Base ?'
printn
call inputnumber ;Enter the base the number resides in 
mov bx,ax ; base is stored in bx

pop ax ; orginal number is restored to ax

call other_to_decimal
call prnt_in_decimal

mov ax,4c00h
int 21h

;---------------------------------------------------------




;input must be stored in ax
;base must be stored in bx
;output will be stored in ax
other_to_decimal:

mov cx,10
mov [2000],10
forstck:
    div [2000];al=ax/10;ah=ax%10    
    mov [2001],ah
    push [2001]
    mov ah,0            
loop forstck

mov cx,10
mov ax,0 ; answer will be stored in ax
decimal:
mul bx ; ax=ax*bx
mov dx,0
pop dx
add ax,dx
    
loop decimal
ret  







;input must be stored in ax
prnt_in_decimal:
mov cx,10
mov bx,10
mov dx,0

forprnt:
    div bx ; ax=ax/10 ; dx=ax%10
    push dx        
    mov dx,0
loop forprnt

mov cx,10           
now_prnt:
    pop dx
    add dx,'0'
    call putc
loop now_prnt
ret


;output will be stored in ax
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




putc:
mov ah,02h
int 21h
ret 


getc:
mov ah,01h
int 21h
ret


end start