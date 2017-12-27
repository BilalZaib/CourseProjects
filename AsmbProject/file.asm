TITLE Flappy Bird Game

; Program Description: Flappy Bird Game
; Author: Bilal Zaib
; Creation Date: 12/5/2015

;--------------------------------------------------------------------------------------------------

;INCLUDE Irvine32.inc
;INCLUDE Macros.inc
;INCLUDELIB User32.lib
;INCLUDE     GraphWin.inc
Include hlpMuse.asm



.data
buffer BYTE 500 DUP(?)
bufSize = ($-buffer)

errMsg BYTE "Cannot open file",0dh,0ah,0
filename     BYTE "Filedata.txt",0
fileHandle   DWORD ?	; handle to output file
byteCount    DWORD ?    	; number of bytes written

.code
file1 PROC
mov eax,400h
	mov edx,0
	mov ebx,1
	.REPEAT
	push eax
	mov eax,0Ah
	call SetTextColor
	pop eax
	mov dh,1				;For Upper wall 'ah'
	mov dl,ah	
	call gotoxy
	mov al,4
	call writechar
	mov dh,22
	call gotoxy
	call writechar
	inc ah
	.IF ah>31 && ah< 48
	mov dh,3				;For Upper wall 'ah'
	mov dl,ah	
	call gotoxy
	mov al,'='
	call writechar	
	.ENDIF
	;push eax
	;INVOKE Sleep,3
	;pop eax
	.UNTIL ah==75
mov edx,0
call gotoxy
;-------------------------------------------------------------------------------------------------------
	INVOKE CreateFile,
	  ADDR filename, GENERIC_READ, DO_NOT_SHARE, NULL,
	  OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, 0

	mov fileHandle,eax		; save file handle
	.IF eax == INVALID_HANDLE_VALUE
	  mov  edx,OFFSET errMsg		; Display error message
	  call WriteString
	  jmp  QuitNow
	.ENDIF

	INVOKE ReadFile,		; write text to file
	    fileHandle,		; file handle
	    ADDR buffer,		; buffer pointer
	    bufSize,		; number of bytes to write
	    ADDR byteCount,		; number of bytes written
	    0		; overlapped execution flag

	INVOKE CloseHandle, fileHandle
	mov esi,byteCount
	mov buffer[esi],0		; into buffer
	mov esi,0		; insert null terminator
	mov ecx,byteCount
	L1:
	push ecx
	.IF esi>35 && esi<52		
		mov eax,0Fh			;For Line
	.ELSEIF esi>53 && esi<75 || esi> 93 && esi<116 || esi>139 && esi<164
		mov eax,0Eh			;Headings Color
	.ELSEIF esi>79 && esi<93 || esi>116 && esi<139
		mov eax,0Bh			;Paragraph Color
		push eax
		INVOKE Sleep,3
		pop eax
	.ELSEIF esi>164
		mov eax,0Dh			;Group Members
		push eax
		
		INVOKE Sleep,3
		pop eax
	.ELSE
		mov eax,0Ah			;Between Line
	.ENDIF
	call SetTextColor
	mov al,buffer[esi]
	call WriteChar
	INC esi
	pop ecx
	DEC ecx
	CMP ecx,0
	JNZ L1

QuitNow:
mov dh,23
mov dl,30
call gotoxy
	ret
file1 ENDP




;main PROC

;	exit
;main ENDP


;END main