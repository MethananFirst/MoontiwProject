const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
  //  baseUrl:"http://intproj22.sit.kmutt.ac.th/ssi2",
  baseUrl:"http://localhost:5173/",
   experimentalSessionAndOrigin: true,
   experimentalRunAllSpecs:true,
  },
  viewportHeight :1080,
  viewportWidth :1920,
});
