include 'emu8086.inc'
.model small
.code


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
printn

mov bx,ax ; keep the number in bx

print 'Give the divider number '

mov ah,01h   ; collecting number in al
int 21h    

mov ah,0
sub al,'0'
mov cx,ax  ; keep divider  in cx
printn

mov ax,bx  ; collect number from stack 
div cx ; dividing ax by cx
cmp dx,0  ; stored the remainder in dx
je no_remain
jmp remain
 
no_remain:
    printn 'divisible'
    jmp skip
    
remain:
    printn 'not divisible'
skip:
