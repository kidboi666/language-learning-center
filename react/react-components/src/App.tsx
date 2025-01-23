import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Button from "./components/button";
import Input from "./components/input";
import Dropdown from "./components/drop-down";

function App() {
  const [count, setCount] = useState(0);
  const [selectedOption, setSelectedOption] = useState<string | null>(null);

  const options = ["Option 1", "Option 2", "Option 3"];

  const handleSelect = (option: string) => {
    setSelectedOption(option);
  };

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        {selectedOption && <p>Selected option: {selectedOption}</p>}
        <Button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </Button>
        <Dropdown onSelect={handleSelect} options={options} />
        <Input />
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
