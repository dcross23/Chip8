/**
   Credits to Mark Stanley, Alexander Brevig
   This program uses his Keypad library for Arduino 4x4 Keypad
   https://playground.arduino.cc/Code/Keypad/#Download
*/

#include <Keypad.h>

const byte ROWS = 4;
const byte COLUMNS = 4;
char keys[ROWS][COLUMNS] = {
  {'1', '2', '3', 'C'},
  {'4', '5', '6', 'D'},
  {'7', '8', '9', 'E'},
  {'A', '0', 'B', 'F'}
};

byte rowsConexions[ROWS] = {11, 10, 9, 8};
byte columnConexions[COLUMNS] = {7, 6, 5, 4};
Keypad keyPad = Keypad(makeKeymap(keys), rowsConexions, columnConexions, ROWS, COLUMNS);

void setup() {
  Serial.begin(9600);
  keyPad.addEventListener(keyPadEvent);
}

void loop() {
  char pressedKey = keyPad.getKey();   
  delay(20);
}


/**
 * Event that will be launch when a key is pressed.
 * It sends through serial conexion the key pressed/released
 * for Chip8 program.
 * 2 codes for chip8 interpreter to know the key and 
 * the action: 
 * key      : if the key "key" is pressed
 * key+'0'  : if the key "key" is released
 * 
 * Then Chip8 program with "decode" this and interpret this.
 */
void keyPadEvent(KeypadEvent key) {
  char toPrint = key;
  switch (keyPad.getState()) {
    case PRESSED:
      Serial.print(toPrint);
      break;

    case RELEASED:
      toPrint += '0';
      Serial.print(toPrint);
      break;
  }
}
