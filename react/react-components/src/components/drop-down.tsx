import React, { useState, useEffect, useRef } from "react";
import styled from "styled-components";

interface DropdownProps {
  options: string[];
  onSelect: (option: string) => void;
}

const DropdownContainer = styled.div`
  position: relative;
  display: inline-block;
  font-family: "Arial", sans-serif;
`;

const DropdownButton = styled.button`
  background-color: #007bff;
  color: white;
  padding: 12px 20px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
  display: flex;
  align-items: center;
  justify-content: space-between;

  &:hover {
    background-color: #0056b3;
    transform: translateY(-2px);
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  }

  &:active {
    background-color: #004085;
    transform: translateY(0);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
`;

const Arrow = styled.span<{ isOpen: boolean }>`
  margin-left: 10px;
  border: solid white;
  border-width: 0 2px 2px 0;
  display: inline-block;
  padding: 3px;
  transform: ${(props) => (props.isOpen ? "rotate(-135deg)" : "rotate(45deg)")};
  transition: transform 0.3s ease;
`;

const DropdownMenu = styled.ul<{ isOpen: boolean }>`
  display: ${(props) => (props.isOpen ? "block" : "none")};
  position: absolute;
  background-color: white;
  min-width: 200px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  border-radius: 4px;
  padding: 0;
  margin: 10px 0 0 0;
  list-style: none;
  opacity: ${(props) => (props.isOpen ? 1 : 0)};
  transform: ${(props) =>
    props.isOpen ? "translateY(0)" : "translateY(-10px)"};
  transition: opacity 0.3s ease, transform 0.3s ease;
`;

const DropdownItem = styled.li`
  color: #333;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  &:hover {
    background-color: #f1f1f1;
    color: #007bff;
  }
`;

const Dropdown: React.FC<DropdownProps> = ({ options, onSelect }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState<string | null>(null);
  const dropdownRef = useRef<HTMLDivElement>(null);

  const handleOptionClick = (option: string) => {
    setSelectedOption(option);
    onSelect(option);
    setIsOpen(false);
  };

  const handleClickOutside = (event: MouseEvent) => {
    if (
      dropdownRef.current &&
      !dropdownRef.current.contains(event.target as Node)
    ) {
      setIsOpen(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <DropdownContainer ref={dropdownRef}>
      <DropdownButton onClick={() => setIsOpen(!isOpen)}>
        {selectedOption || "Select an option"}
        <Arrow isOpen={isOpen} />
      </DropdownButton>
      <DropdownMenu isOpen={isOpen}>
        {options.map((option) => (
          <DropdownItem key={option} onClick={() => handleOptionClick(option)}>
            {option}
          </DropdownItem>
        ))}
      </DropdownMenu>
    </DropdownContainer>
  );
};

export default Dropdown;
