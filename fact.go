package main

import (
	"bufio"
	"fmt"
	"os"
	"time"

	"github.com/labstack/echo/v4"
)

func HandleGetFact(c echo.Context) error {
	return c.JSON(200, getFact())
}

func getFact() string {
	errStr := "error"
	file, err := os.Open("fact-data.txt")
	if err != nil {
		fmt.Println("Error opening file: ", err)
		return errStr
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var facts []string

	for scanner.Scan() {
		facts = append(facts, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading existing file: ", err)
		return errStr
	}

	randomIndex := time.Now().UnixMilli() % int64(len(facts))
	return facts[randomIndex]
}
