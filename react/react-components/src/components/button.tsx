import { ComponentProps } from "react";
import styled from "styled-components";

const ButtonContainer = styled.button`
  background-color: #007bff;
  color: white;
  padding: 12px 24px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;

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

  &:disabled {
    background-color: #c0c0c0;
    cursor: not-allowed;
    box-shadow: none;
  }
`;

interface ButtonProps extends ComponentProps<"button"> {
  className?: string;
}

const Button: React.FC<ButtonProps> = ({ className, children, ...props }) => {
  return (
    <ButtonContainer className={className} {...props}>
      {children}
    </ButtonContainer>
  );
};

export default Button;
