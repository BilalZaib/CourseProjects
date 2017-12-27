TITLE Flappy Bird Game

; Program Description: Flappy Bird Game
; Author: Bilal Zaib
; Creation Date: 12/5/2015

;--------------------------------------------------------------------------------------------------

INCLUDE tringl.asm

VK_ESCAPE		EQU		00000001bh
VK_LBUTTON		EQU		000000001h
VK_RBUTTON		EQU		000000002h

GetCursorPos PROTO, lpPoint:DWORD
ScreenToClient PROTO, hWnd:DWORD, lpPoint:DWORD
GetConsoleWindow            PROTO
GetKeyState                 PROTO :DWORD

.data
    cursorPos POINT <?,?>
    hwndConsole DWORD ?
    ;hStdOut DWORD ?
    QtPl	BYTE ?
.code
MouseDemo PROC

	     	
    INVOKE  GetConsoleWindow
    mov     hwndConsole,eax
    call PAGE2
     looop:
         mGotoxy 0,0
     
         INVOKE GetCursorPos, ADDR cursorPos         
         invoke ScreenToClient, hwndConsole, ADDR cursorPos
         invoke Sleep, 20
         .IF ( cursorPos.X>244 && cursorPos.X<380 ) && (cursorPos.Y>115 && cursorPos.Y<157)
	         INVOKE  GetKeyState,VK_LBUTTON
	         .iF ah
	             mov QtPl,1
	             mov eax,0Fh
		     call settextcolor
	             ret
	         .ENDIF
        .ENDIF
         .IF ( cursorPos.X>266 && cursorPos.X<357 ) && (cursorPos.Y>198 && cursorPos.Y<245)
         	INVOKE  GetKeyState,VK_LBUTTON
        .iF ah
            mov QtPl,2
            mov eax,0Fh
	    call settextcolor
            ret
        .ENDIF
        .ENDIF
              
     jmp looop

	ret
MouseDemo ENDP
