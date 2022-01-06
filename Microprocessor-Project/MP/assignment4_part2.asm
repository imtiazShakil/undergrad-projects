include 'emu8086.inc'
.model small
.code
print 'Number ?'    
printn
call input_num ; take input stored in ax
push ax  ; the number is stored in stack
print 'Base ?'
printn
call input_num ; base is stored in ax

mov bx,ax ; base is stored in bx
pop ax ; actual number is restored in ax
base dw ?  ; declare a variable
mov base,10
mov cx,10 
convert:
    div base ;actual num is divided by base num
    push dx ; remainder in dx and stored in the stack
    mov dx,0
loop convert  
mov cx,10
mov ax,0 ; recovering the decimal number in ax
decimal:
    mul bx 
    mov dx,0
    pop dx
    add ax,dx    
loop decimal

mov cx,10
mov bx,10
mov dx,0

forming_stack:
    div bx 
    push dx        
    mov dx,0
loop forming_stack

mov cx,10           
printing:
    pop dx
    add dx,'0'
    mov ah,02h
    int 21h
loop printing 

mov ax,4c00h
int 21h

input_num:
print 'Digits? '
mov ah,01h
int 21h  ;digits number stored in al

printn
mov cx,0
mov cl,al
sub cl,'0' ; store that number in cx for controlling the loop

print 'Give the number '
mov ax,0   ; initially previous value is set to 0
input:

    mov dx,ax  ; keeping previous value in dx

    mov ah,01h
    int 21h  ; collecting digit

    sub al,'0'
    mov bx,0
    mov bl,al    ; digit is stored in bx

    mov ax,dx      ; collect previous value
    mov dx,10

    mul dx ; forming the number multiplying by 10

    add ax,bx

loop input 

printn
ret
