export function sample<T>(numbers: T[]): T {
  const idx = Math.floor(Math.random() * numbers.length);
  return numbers[idx];
}

export function add(x: number, y: number) {
  return x + y;
}

export const pi = 3.14;