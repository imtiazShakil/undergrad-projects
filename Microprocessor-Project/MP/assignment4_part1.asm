include 'emu8086.inc'
.model small
.code
print 'Number ?'
call input_num ; take input stored in ax
push ax  ; the number is stored in stack
print 'Base ?'
printn
call input_num ; base is stored in ax

mov bx,ax ; base is stored in bx
pop ax ; actual number is restored in ax

mov cx,10 
convert:
    div bx ;actual num is divided by base num
    push dx ; remainder in dx and stored in the stack
    mov dx,0
loop convert

mov cx,10
output:
    pop dx ; restore remainder from stack
    cmp dx,9 
    ja char ; has to add character
    jb num  ; has to add digit
    
    char:
        add dx,55 ; as difference is 55 from 10h to 'A'
        jmp skp
    num:
        add dx,'0' 
    
    skp:
        mov ah,02h
        int 21h    
loop output 

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
