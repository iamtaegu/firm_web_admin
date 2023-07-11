import "./Body.css"

const Body = ({name, location, favorities}) => {
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

export default Body;
