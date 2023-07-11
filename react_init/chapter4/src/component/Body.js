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

function Body() {

    console.log("컴포넌트 업데이트 ! ");

    const [count, setCount] = useState(0);

    const onIncerease = () => {
        setCount(count+1);
    }

    return (
        <div>
            <h2>{count}</h2>
            <button onClick={onIncerease}> + </button>
        </div>
    )
}

export default Body;
