import './App.css';
import { useState, useEffect, useRef } from "react";
import Controller from "./component/Controller";
import Viewer from "./component/Viewer";
import Even from "./component/Even";

function App() {
  const [count, setCount] = useState(0);
  const [text, setText] = useState("");
  const handleSetCount = (value) => {
    setCount(count + value);
  };
  const handleChangeText = (e) => {
      setText(e.target.value);
  };

  const didMountRef = useRef(false); //마운트 초기값 false
  // callback, dependency arrays
  useEffect( () => {
     if (!didMountRef.current) { //페이지 마운트 일때는 출력하지 않음
         didMountRef.current = true;
         return;
     } else { //페이지 업데이트 일때는 출력
         console.log("count 업데이트: ", text, count);
     }
  }, [count,text]);

  // 컴포넌트 마운트 시점에 실행(컴포넌트 마운트 제어)
  useEffect(() => {
      console.log("컴포넌트 마운트");
  }, []);

  // 컴포넌트 언마운트 시점 클린업
  useEffect(() => {
      const intervalID = setInterval(() => {
          console.log("깜빡");
      }, 1000);

      return () => {
          console.log("클린업");
          clearInterval(intervalID);
      }
  });

  return (
    <div className="App">
      <h1>Simple Counter</h1>
      <section>
          <input value={text} onChange={handleChangeText}/>
      </section>
      <section>
        <Viewer count={count} />
          {count %2 == 0 && <Even />}
      </section>
      <section>
        <Controller handleSetCount={handleSetCount} />
      </section>
    </div>
  );
}

export default App;
