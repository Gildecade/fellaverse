#!/usr/bin/perl
use strict;
use warnings;
use 5.010;
 
use Spreadsheet::Read qw(ReadData);
# install modules:
# cpan install Spreadsheet::Read 
# cpan install Spreadsheet::XLSX
my $book = ReadData('tests.xlsx');
my $token = "";
my $abs_path = "";
my $module_name = "";
my $test_path = "";
my $test_name = "";
my $method_name = "";
my $type = "";
my $body = "";
my $url = "";
my $debug = $ARGV[0];
 
#say 'A1: ' . $book->[1]{A1}; 
 
my @row = Spreadsheet::Read::row($book->[1], 1);
my @rows = Spreadsheet::Read::rows($book->[1]);

$token = $rows[1-1][2-1];
$abs_path = $rows[1-1][4-1];
foreach my $i (3 .. scalar @rows) {
    if (!$rows[$i-1][1-1]) {
        next;
    }
    $module_name = $rows[$i-1][1-1];
    $test_path = $abs_path . "/" . $rows[$i-1][2-1];
    $test_name = $rows[$i-1][3-1];
    $method_name = $rows[$i-1][4-1];
    $type = $rows[$i-1][5-1];
    $body = $rows[$i-1][6-1];
    $url = $rows[$i-1][7-1];


    if (defined $debug && $ARGV[0] == "-debug"  ) {
        say "module name: " . "$module_name\n";
        say "test path: " . "$test_path\n";
        say "test name: " . "$test_name\n";
        say "method name: " . "$method_name\n";
        say "request type: " . "$type\n";
        if (defined $body && length $body != 0) {
            say "body: " . "$body\n";
        }
        say "url: " . "$url\n";
        say "file abs path: " . "$test_path/$test_name.java";
    }
    # Open a file handle for writing
    open(my $output_file, '>', "$test_path/$test_name.java") or die "Cannot open file: $!";

        # Use print statement to write data to file
        #print $output_file chr(64+$i) . " $j " . ($rows[$i-1][$j-1] // '');
    print $output_file "package com.fellaverse.backend.controller;\n";
    print $output_file "\n";
    print $output_file "import com.fellaverse.backend.$module_name;\n";
    print $output_file "import com.mashape.unirest.http.HttpResponse;\n";
    print $output_file "import com.mashape.unirest.http.Unirest;\n";
    print $output_file "import lombok.extern.slf4j.Slf4j;\n";
    print $output_file "import org.junit.jupiter.api.*;\n";
    print $output_file "import org.junit.jupiter.api.extension.ExtendWith;\n";
    print $output_file "import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;\n";
    print $output_file "import org.springframework.boot.test.context.SpringBootTest;\n";
    print $output_file "import org.springframework.test.context.junit.jupiter.SpringExtension;\n";
    print $output_file "import org.springframework.test.context.web.WebAppConfiguration;\n";
    print $output_file "\n";
    print $output_file "\@ExtendWith(SpringExtension.class)\n";
    print $output_file "\@WebAppConfiguration\n";
    print $output_file "\@SpringBootTest(classes = $module_name.class)\n";
    print $output_file "\@AutoConfigureMockMvc\n";
    print $output_file "\@Slf4j\n";
    print $output_file "\@DisplayName(\"$test_name Test\")\n";
    print $output_file "\@TestInstance(TestInstance.Lifecycle.PER_CLASS)\n";
    print $output_file "class $test_name {\n";
    print $output_file "    \@Test\n";
    print $output_file "    void $method_name() throws Exception {\n";
    print $output_file "        HttpResponse<String> response = Unirest.$type(\"$url\")\n";
    print $output_file "                .header(\"fellaverse-token\", \"$token\")\n";
    print $output_file "                .header(\"content-type\", \"application/json\")\n";
    if (defined $body && length $body != 0) {
        print $output_file "                .body(\"$body\")\n";
    }
    print $output_file "                .asString();\n";
    print $output_file "        System.out.println(response.getStatus());\n";
    print $output_file "        System.out.println(response.getBody());\n";
    print $output_file "        Assertions.assertEquals(200, response.getStatus());\n";
    print $output_file "        Assertions.assertNotNull(response.getBody());\n";
    print $output_file "    }\n";
    print $output_file "}\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    print $output_file "\n";
    
    # Close the file handle
    close $output_file;
}



 
