<template>
  <div>
    <category-nav></category-nav>
    <category-book-list :books="books"> </category-book-list>
  </div>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },
  data: function () {
    return {
      books: [
        {
          bookId: 1001,
          title: "Cracking the Coding Interview",
          imageFile: "cracking-gayle.jpg",
          author: "Gayle McDowell",
          price: 2449,
          isPublic: true,
        },
        {
          bookId: 1002,
          title:
            "The Bitcoin Standard: The Decentralized Alternative to Central Banking",
          imageFile: "bitcoin-saifedean.jpg",
          author: "Saifedean Ammous",
          price: 1399,
          isPublic: false,
        },
        {
          bookId: 1003,
          title:
            "Crypto Investing Guide: How to Invest in Bitcoin, DeFi, NFTs, and More",
          imageFile: "crypto-ian.jpg",
          author: "Ian Balina",
          price: 2299,
          isPublic: false,
        },
        {
          bookId: 1004,
          title:
            "Launch: How to Sell Almost Anything Online, Build a Business You Love, and Live the Life of Your Dream",
          imageFile: "launch-jeff.jpg",
          author: "Jeff Walker",
          price: 1299,
          isPublic: false,
        },
        {
          bookId: 1005,
          title:
            "System Error: Where Big Tech Went Wrong and How We Can Reboot",
          imageFile: "system-rob.jpg",
          author: "Rob Reich, Mehran Sahami, Jeremy M. Weinstein",
          price: 2349,
          isPublic: true,
        },
        {
          bookId: 1006,
          title:
            "The Experience Maker: How to Create Remarkable Experiences That Your Customers Can’t Wait to Share",
          imageFile: "experience-dan.jpg",
          author: "Dan Gingiss",
          price: 2695,
          isPublic: true,
        },
      ],
    };
  },
};
</script>

<style scoped></style>
