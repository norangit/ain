var positiveNumber = /^\d+(\.\d+|\d*)$/i;
var notEmptyString = /^(?!\s*$).+/i;
var emptyString = /^\s*$/i;

function onlyNumber() {
  onKeyup(evt) {
    const reg = /[^\.\d]/g;
    const dotReg = /\./g;
    const key = evt.key;
    const value = evt.target.value;

    if (reg.test(key)) {
      evt.target.value = value.replace(key, '');
    } else {
      if (key === '.' && value.match(dotReg).length > 1) {
        const dotIndex = value.lastIndexOf('.');
        evt.target.value = value.substring(0, dotIndex) + value.substring(dotIndex + 1);
      }
    }
  }
}