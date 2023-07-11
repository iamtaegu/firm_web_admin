// 리액트 컴포넌트와 HTML 태그를 구분하기 위해 항상 대문자로 시작
const Footer = ({children}) => {
    return (
        <footer>
            <h1>footer</h1>
            {children}
        </footer>
    );
};

export default Footer;
