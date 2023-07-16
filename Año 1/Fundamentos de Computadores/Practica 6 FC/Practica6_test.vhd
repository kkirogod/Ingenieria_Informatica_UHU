--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   11:53:21 12/20/2021
-- Design Name:   
-- Module Name:   D:/UHU/FC/Practica 6 FC/Practica6_test.vhd
-- Project Name:  Practica_6
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: Practica_6
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY Practica6_test IS
END Practica6_test;
 
ARCHITECTURE behavior OF Practica6_test IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT Practica_6
    PORT(
         D0 : IN  std_logic;
         D1 : IN  std_logic;
         D2 : IN  std_logic;
         D3 : IN  std_logic;
         P0 : IN  std_logic;
         P1 : IN  std_logic;
         FF : OUT  std_logic;
         salida_dec : OUT  std_logic_vector(6 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal D0 : std_logic := '0';
   signal D1 : std_logic := '0';
   signal D2 : std_logic := '0';
   signal D3 : std_logic := '0';
   signal P0 : std_logic := '0';
   signal P1 : std_logic := '0';

 	--Outputs
   signal FF : std_logic;
   signal salida_dec : std_logic_vector(6 downto 0);
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: Practica_6 PORT MAP (
          D0 => D0,
          D1 => D1,
          D2 => D2,
          D3 => D3,
          P0 => P0,
          P1 => P1,
          FF => FF,
          salida_dec => salida_dec
        );

   -- Clock process definitions
  D0 <= '0' after 100 ns, '0' after 200 ns, '0' after 300 ns, '1' after 400 ns, '1' after 500 ns;
  D1 <= '0' after 100 ns, '0' after 200 ns, '0' after 300 ns, '0' after 400 ns, '0' after 500 ns;
  D2 <= '0' after 100 ns, '1' after 200 ns, '1' after 300 ns, '0' after 400 ns, '0' after 500 ns;
  D3 <= '0' after 100 ns, '0' after 200 ns, '0' after 300 ns, '1' after 400 ns, '1' after 500 ns;
  P0 <= '0' after 100 ns, '0' after 200 ns, '1' after 300 ns, '1' after 400 ns, '1' after 500 ns; 
  P1 <= '0' after 100 ns, '0' after 200 ns, '1' after 300 ns, '1' after 400 ns, '1' after 500 ns;


END;
