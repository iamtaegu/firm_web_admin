/**
 * ESM에서는 export 키워드를 붙이면 내보낼 수 있음
 */

const PI = 3.141592;

function getArea(radius) {
    return PI * radius * radius;
}

function getCircumference(radius) {
    return 2 * PI * radius;
}

export {PI, getArea, getCircumference};
