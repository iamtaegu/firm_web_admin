import "./Body.css"
import { useState } from "react";

/*const Body = ({name, location, favorities}) => {
    //const {name, location} = props;
    return (
        <div className="body">
            <h1>{name}, {location}, {favorities.length}</h1>
        </div>
    );
};

Body.defaultProps = {
    favorities: [],
}
*/

// 이벤트 처리하기
/*function Body() {
    function handleOnClick(e) {
        console.log(e);
        console.log(e.target.name);
    }

    return (
        <div className="body">
            <button name="A버튼" onClick={handleOnClick} >
                A버튼
            </button>
            <button name="B버튼" onClick={handleOnClick} >
                B버튼
            </button>
        </div>
    );
}*/

function Viewer({number}) {
    console.log(number);
    return <div>{number % 2 === 0 ? <h3>짝수</h3> : <h3>홀수</h3>}</div>;
}

function Body() {

    console.log("컴포넌트 업데이트 ! ");

    const [number, setNumber] = useState(0);
    const onIncerease = () => {
        setNumber(number+1);
    };
    const onDecrease = () => {
        setNumber(number-1);
    };

    return (
        <div>
            <h2>{number}</h2>
            <Viewer number={number}/>
            <div>
                <button onClick={onDecrease}> - </button>
                <button onClick={onIncerease}> + </button>
            </div>
        </div>
    )
}

export default Body;
