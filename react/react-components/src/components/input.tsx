import React from "react";
import styled from "styled-components";

const StyledInput = styled.input`
  padding: 12px 15px;
  border: 1px solid #ccc;
  border-radius: 25px;
  font-size: 16px;
  width: 100%;
  box-sizing: border-box;
  transition: all 0.3s ease;
  background-color: #f9f9f9;

  &:focus {
    border-color: #007bff;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    background-color: #fff;
  }

  &:hover {
    border-color: #0056b3;
  }
`;

const Input: React.FC<React.InputHTMLAttributes<HTMLInputElement>> = (
  props
) => {
  return <StyledInput {...props} />;
};

export default Input;
