        // Function to generate a random character
        function getRandomCharacter() {
            const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            const randomIndex = Math.floor(Math.random() * characters.length);
            return characters[randomIndex];
        }

        // Function to generate a 2D array of random characters
        function generateRandomCharacterGrid(rows, cols) {
            const grid = [];
            for (let i = 0; i < rows; i++) {
                const row = [];
                for (let j = 0; j < cols; j++) {
                    row.push(getRandomCharacter());
                }
                grid.push(row);
            }
            return grid;
        }

        // Function to display the character grid on the website
        function displayCharacterGrid(grid) {
            const characterGridDiv = document.getElementById("characterGrid");
            characterGridDiv.innerHTML = "";

            for (let i = 0; i < grid.length; i++) {
                const rowDiv = document.createElement("div");
                for (let j = 0; j < grid[i].length; j++) {
                    const cellSpan = document.createElement("span");
                    cellSpan.textContent = grid[i][j] + "   ";
                    rowDiv.appendChild(cellSpan);
                }
                characterGridDiv.appendChild(rowDiv);
            }
        }

        function gridifyMaze(string, rows, cols) {
            const grid = [];
            for (let i = 0; i < rows; i++) {
                const row = [];
                for (let j = 0; j < cols; j++) {
                    let value = (i * rows) + j;
                    row.push(string.charAt(value));
                }
                grid.push(row);
            }
            return grid;
        }

        // Define the size of the grid (change these values as needed)
        const numRows = 10;
        const numCols = 10;
        const mazeMess = "1111111111b------1-11--1-1---11-111111111-1----1-11-1--1-1-11-1111-1-11----1---11-1----1-e1111111111";

        let grid = gridifyMaze(mazeMess, numRows, numCols);


        // Generate the random character grid and display it
        const randomCharacterGrid = generateRandomCharacterGrid(numRows, numCols);
        displayCharacterGrid(grid);

// let container = document.getElementById("array");
// // Function to randomly shuffle the array
// function shuffle(arr) {
//     for (let i = arr.length - 1; i > 0; i--) {
 
//         // Generate random number
//         let j = Math.floor(Math.random() * (i + 1));
//         let temp = arr[i];
//         arr[i] = arr[j];
//         arr[j] = temp;
//     }
// }
// function generatearray() {
 
//     // Creating an array
//     let arr = [];
 
//     // Filling array with values from 1 to 20
//     for (let i = 0; i < 20; i++) {
//         arr.push(i + 1);
//     }
 
//     // Shuffling the array
//     shuffle(arr);
//     for (let i = 0; i < 20; i++) {
//         let value = arr[i];
 
//         // Creating element div
//         let array_ele = document.createElement("div");
 
//         // Adding class 'block' to div
//         array_ele.classList.add("block");
 
//         // Adding style to div
//         array_ele.style.height = `${value * 13}px`;
//         array_ele.style.transform = `translate(${i * 30}px)`;
 
//         // Creating label element for displaying
//         // size of particular block
//         let array_ele_label = document.createElement("label");
//         array_ele_label.classList.add("block_id");
//         array_ele_label.innerText = value;
 
//         // Appending created elements to index.html
//         array_ele.appendChild(array_ele_label);
//         container.appendChild(array_ele);
//     }
// }
 
// async function InsertionSort(clsnam, delay = 600) {
//     let blocks = document.getElementsByClassName(clsnam);
//     blocks[0].style.backgroundColor = "rgb(49, 226, 13)";
 
//     for (let i = 1; i < blocks.length; i += 1) {
//         let j = i - 1;
 
//         // To store the integer value of ith block to key
//         let key = parseInt(blocks[i].childNodes[0].innerHTML);
 
//         // To store the ith block height to height
//         let height = blocks[i].style.height;
 
//         // Provide darkblue color to the ith block
//         blocks[i].style.backgroundColor = "darkblue";
 
//         // To pause the execution of code for 600 milliseconds
//         await new Promise((resolve) =>
//             setTimeout(() => {
//                 resolve();
//             }, 600)
//         );
//         // For placing selected element at its correct position
//         while (j >= 0 && parseInt(blocks[j].childNodes[0].innerHTML) > key) {
 
//             // Provide darkblue color to the jth block
//             blocks[j].style.backgroundColor = "darkblue";
 
//             // For placing jth element over (j+1)th element
//             blocks[j + 1].style.height = blocks[j].style.height;
//             blocks[j + 1].childNodes[0].innerText =
//                 blocks[j].childNodes[0].innerText;
//             j = j - 1;
 
//             // To pause the execution of code for 600 milliseconds
//             await new Promise((resolve) =>
//                 setTimeout(() => {
//                     resolve();
//                 }, delay)
//             );
 
//             // Provide lightgreen color to the sorted part
//             for (let k = i; k >= 0; k--) {
//                 blocks[k].style.backgroundColor = " rgb(49, 226, 13)";
//             }
//         }
//         // Placing the selected element to its correct position
//         blocks[j + 1].style.height = height;
//         blocks[j + 1].childNodes[0].innerHTML = key;
 
//         // To pause the execution of code for 600 milliseconds
//         await new Promise((resolve) =>
//             setTimeout(() => {
//                 resolve();
//             }, delay)
//         );
//         // Provide light green color to the ith block
//         blocks[i].style.backgroundColor = " rgb(49, 226, 13)";
//     }
// }
// // Asynchronous CountingSort function
// async function CountingSort(delay = 250) {
//     let blocks = document.querySelectorAll(".block");
 
//    let block1 = 0,
//         block2 = 0,
//         block3 = 0,
//         block4 = 0;
 
//     // CountingSort Algorithm
//     for (let i = 0; i < blocks.length; i += 1) {
//         blocks[i].style.backgroundColor = "#FF4949";
//         let value =
//             Number(blocks[i].childNodes[0].innerHTML);
 
//         // Creating element div
//         let array_ele = document.createElement("div");
 
//         // Adding style to div
//         array_ele.style.height = `${value * 13}px`;
 
//         // Creating label element for displaying
//         // size of particular block
//         let array_ele_label = document.createElement("label");
//         array_ele_label.classList.add("block_id");
//         array_ele_label.innerText = value;
 
//         array_ele.appendChild(array_ele_label);
 
//         // Adding block to first bucket
//         if (value >= 1 && value <= 5) {
//             array_ele.classList.add("firstbucket");
//             var container = document.getElementById("one");
//             array_ele.style.transform =
//                 `translate(${block1 * 30}px)`;
//             container.appendChild(array_ele);
//             block1++;
//         }
 
//         // Adding block to second bucket
//         if (value >= 6 && value <= 10) {
//             array_ele.classList.add("secondbucket");
//             var container = document.getElementById("two");
//             array_ele.style.transform =
//                 `translate(${block2 * 30}px)`;
//             container.appendChild(array_ele);
//             block2++;
//         }
 
//         // Adding block to third bucket
//         if (value >= 11 && value <= 15) {
//             array_ele.classList.add("thirdbucket");
//             var container = document.getElementById("three");
//             array_ele.style.transform = `translate(${block3 * 30}px)`;
//             container.appendChild(array_ele);
//             block3++;
//         }
 
//         // Adding block to fourth bucket
//         if (value >= 16 && value <= 20) {
//             array_ele.classList.add("fourthbucket");
//             var container = document.getElementById("four");
//             array_ele.style.transform =
//                 `translate(${block4 * 30}px)`;
//             container.appendChild(array_ele);
//             block4++;
//         }
 
//         // To wait for 250 milliseconds
//         await new Promise((resolve) =>
//             setTimeout(() => {
//                 resolve();
//             }, delay)
//         );
 
//         blocks[i].style.backgroundColor = "#6b5b95";
//     }
 
//     // Performing insertion sort on every bucket
//     await InsertionSort("firstbucket");
//     await InsertionSort("secondbucket");
//     await InsertionSort("thirdbucket");
//     await InsertionSort("fourthbucket");
 
//     // Copying elements from buckets to main array
//     for (let i = 0; i < 4; i++) {
//         var bucket_idx = 0;
//         var block_idx;
//         if (i == 0) block_idx =
//             document.getElementsByClassName("firstbucket");
//         if (i == 1) block_idx =
//             document.getElementsByClassName("secondbucket");
//         if (i == 2) block_idx =
//             document.getElementsByClassName("thirdbucket");
//         if (i == 3) block_idx =
//             document.getElementsByClassName("fourthbucket");
//         for (var j = i * 5; j < 5 * (i + 1); j++, bucket_idx++) {
//             block_idx[bucket_idx].style.backgroundColor = "red";
 
//             // To wait for 300 milliseconds
//             await new Promise((resolve) =>
//                 setTimeout(() => {
//                     resolve();
//                 }, 300)
//             );
 
//             blocks[j].style.height =
//                 block_idx[bucket_idx].style.height;
//             blocks[j].childNodes[0].innerText =
//                 block_idx[bucket_idx].childNodes[0].innerText;
//             blocks[j].style.backgroundColor = "green";
 
//             // To wait for 300 milliseconds
//             await new Promise((resolve) =>
//                 setTimeout(() => {
//                     resolve();
//                 }, 300)
//             );
 
//             block_idx[bucket_idx]
//                 .style.backgroundColor = "#6b5b95";
//         }
//     }
// }
 
// // Calling generatearray function
// generatearray();
 
// // Calling CountingSort function
// CountingSort();