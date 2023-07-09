import './App.css';
import Header from "./component/Header"
import Body from "./component/Body"
import Footer from "./component/Footer"

/**
 * 리액트이 컴포넌트는
 * HTML을 반환하는 메소드를 칭함
 *
 * <App> 루트 컴포넌트
 * <Header> 자식 컴포넌트를 배치
 */
function App() {
  return (
    <div className="App">
        <Header />
        <Body />
        <Footer />
    </div>
  );
}

export default App;
