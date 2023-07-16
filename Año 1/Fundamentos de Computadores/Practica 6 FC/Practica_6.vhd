----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:02:32 12/19/2021 
-- Design Name: 
-- Module Name:    Practica_6 - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Practica_6 is
    Port ( D0 : in  STD_LOGIC;
           D1 : in  STD_LOGIC;
           D2 : in  STD_LOGIC;
           D3 : in  STD_LOGIC;
           P0 : in  STD_LOGIC;
           P1 : in  STD_LOGIC;
           FF : out  STD_LOGIC;
			  salida_dec: out STD_LOGIC_VECTOR (6 downto 0));
end Practica_6;

architecture Behavioral of Practica_6 is
	
	signal dinero_int: integer range 0 to 15;
	signal dinero: std_logic_vector (3 downto 0);
	signal producto: std_logic_vector (1 downto 0);
	signal precio_prod: integer range 0 to 15;
	signal diferencia: integer range -7 to 7;
	
begin
	
	dinero <= D3 & D2 & D1 & D0;
	dinero_int <= conv_integer (dinero);
	producto <= P1 & P0;
	
	with producto select
		precio_prod <=		2 when "00",
								3 when "01",
								5 when "10",
								6 when "11",
								0 when others;
	
	diferencia <= dinero_int - precio_prod;
	
	salida_dec <=	"0111111" when diferencia = 0 else
						"0000110" when diferencia = 1 or diferencia = -1 else
						"1011011" when diferencia = 2 or diferencia = -2 else
						"1001111" when diferencia = 3 or diferencia = -3 else
						"1100110" when diferencia = 4 or diferencia = -4 else
						"1101101" when diferencia = 5 or diferencia = -5 else
						"1111101" when diferencia = 6 or diferencia = -6 else
						"0000111" when diferencia = 7 or diferencia = -7 else
						"0000000";
						
	FF <=		'1' when precio_prod > dinero_int else
				'0';	

end Behavioral;

